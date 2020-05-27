package com.rchytas.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class OWLToCSVRecursion {

	private static final String LOG4J_PROPERTIES_FILE = "../config/log4j.properties";

	private Logger logger = Logger.getLogger(OWLToCSVRecursion.class);

	private static final File ONTOLOGY_IRI = new File(
			"C:/owl files/OWLModel.owl");

	private static OWLOntologyManager manager;

	private static OWLOntology ontology;

	private static String[] metadata;

	private static final String INDIVIDUAL_PARENT = "individual_parent";

	private static final String INDIVIDUAL_PARENT_CLASS = "individual_parent_class";

	private static final String INDIVIDUAL = "individual";

	private static final String INDIVIDUAL_CLASS = "individual_class";

	@Option(name = "-csvfile", required = true, usage = "CSV File Name")
	private String CSVFileName;

	@Option(name = "-owlClass", required = true, usage = "OWL Class")
	private String OWLClass;

	@Option(name = "-owlIndividual", required = false, usage = "OWL Individual")
	private String OWLIndividualExport;

	@Option(name = "-individualExport", required = false, usage = "OWL Individual Export")
	private boolean IndividualExport;

	/**
	 * 
	 * @param args
	 */
	public void run(String[] args) {
		CmdLineParser parser = new CmdLineParser(this);

		try {
			parser.parseArgument(args);

			Properties log4jProperties = new Properties();
			log4jProperties.load(new FileInputStream(LOG4J_PROPERTIES_FILE));
			PropertyConfigurator.configure(log4jProperties);

			String message = String.format("/*** Utility %1s Started ***/",
					getClass().getSimpleName());

			logger.info(message);

			// Load the ontology to export
			manager = OWLManager.createOWLOntologyManager();
			ontology = manager.loadOntologyFromOntologyDocument(ONTOLOGY_IRI);

			message = ("Loaded ontology: " + ontology.getOntologyID());

			// initialize CSV writer
			CSVUtils.initWriter(new FileWriter(CSVFileName));

			// Write Column Names
			writeColumnNames();

			// export OWL class
			exportSingleOWLClass(OWLClass, CSVFileName);

			// close CSV file
			CSVUtils.close();

			logger.info(message);

			message = String.format("/*** Utility %1s Completed ***/",
					getClass().getSimpleName());
			logger.info(message);

		} catch (CmdLineException exception) {
			logger.error(exception.getLocalizedMessage());
			System.err.println(exception.getLocalizedMessage());
			System.err.printf("java %1s [options...]\r\n", getClass()
					.getSimpleName());
			parser.printUsage(System.err);
			System.err.println();
		} catch (Exception exception) {
			logger.error(exception.getLocalizedMessage());
		}
	}

	/**
	 * 
	 * @param OWLClassName
	 */
	private void exportSingleOWLClass(String OWLClassName, String CSVFileName) {
		try {
			String message = String.format("Writing File --> %1s", CSVFileName);
			logger.info(message);

			// Get OWL Classes
			Set<OWLClass> classes = ontology.getClassesInSignature();
			for (OWLClass exportClass : classes) {
				// Export only the user specified OWL Class
				if ((exportClass.toString().equalsIgnoreCase((OWLClassName)))) {
					message = String
							.format("Exporting OWL Classs --> %1s having total instances --> %2s",
									OWLClassName,
									exportClass.getIndividuals(ontology).size());
					logger.info(message);

					Set<OWLIndividual> individuals = exportClass
							.getIndividuals(ontology);
					for (OWLIndividual exportIndividual : individuals) {
						if (IndividualExport
								&& (exportIndividual.toString())
										.equalsIgnoreCase(OWLIndividualExport)) {
							exportSingleIndividual(exportIndividual,
									exportIndividual);
						} else if (!IndividualExport) {
							exportSingleIndividual(exportIndividual,
									exportIndividual);
						}
					}
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error(exception.getLocalizedMessage());
		}

	}

	/**
	 * 
	 * @param individual
	 */
	private void exportSingleIndividual(OWLIndividual individualToExport,
			OWLIndividual individualParent) {
		try {
			String message = String.format(
					"Exporting Individual --> %1s having parent --> %2s",
					individualToExport.toString(), individualParent.toString());
			logger.info(message);

			// get data properties in key value form
			if (individualToExport.getDataPropertyValues(ontology) != null) {
				HashMap<String, String> dataProperties = getOWLIndividualDataProperties(individualToExport);
				dataProperties.put(INDIVIDUAL_PARENT,
						individualParent.toString());
				dataProperties.put(INDIVIDUAL_PARENT_CLASS, individualParent
						.getTypes(ontology).iterator().next().toString());
				dataProperties.put(INDIVIDUAL, individualToExport.toString());
				dataProperties.put(INDIVIDUAL_CLASS, individualToExport
						.getTypes(ontology).iterator().next().toString());
				// Write data properties of this individual to CSV
				writeDataPropertiesToCSV(dataProperties);
			}

			// get object properties if they are not null
			// TODO Individuals of some OWL classes should be ignored ??
			if (individualToExport.getObjectPropertyValues(ontology) != null) {
				Set<OWLIndividual> objectProperties = getOWLIndividualObjectProperties(individualToExport);
				// Recursively iterate
				for (OWLIndividual individualOne : objectProperties)
					exportSingleIndividual(individualOne, individualToExport);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * 
	 * @param individual
	 * @return
	 */
	private HashMap<String, String> getOWLIndividualDataProperties(
			OWLIndividual individual) {

		HashMap<String, String> individualProperties = new HashMap<String, String>();
		String key;
		String value;
		try {
			Map<OWLDataPropertyExpression, Set<OWLLiteral>> data = individual
					.getDataPropertyValues(ontology);
			// TODO OWLLiteral can have multiple values
			for (Entry<OWLDataPropertyExpression, Set<OWLLiteral>> entry : data
					.entrySet()) {
				key = entry.getKey().toString();
				value = entry.getValue().toString();				
				individualProperties.put(
						getDataPropertyFromIRI(key),						
						value.substring(value.indexOf("\"") + 1,
								value.indexOf("^") - 1));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return individualProperties;
	}

	/**
	 * 
	 * @param individual
	 * @return
	 */
	private Set<OWLIndividual> getOWLIndividualObjectProperties(
			OWLIndividual individual) {
		Set<OWLIndividual> objectProperties = new HashSet<OWLIndividual>();
		try {
			Map<OWLObjectPropertyExpression, Set<OWLIndividual>> objects = individual
					.getObjectPropertyValues(ontology);
			for (Entry<OWLObjectPropertyExpression, Set<OWLIndividual>> entry : objects
					.entrySet()) {
				Set<OWLIndividual> objectsOne = entry.getValue();
				for (OWLIndividual individualOne : objectsOne) {
					objectProperties.add((OWLIndividual) individualOne);
				}
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return objectProperties;
	}

	// TODO: Individuals of some OWL classes should be ignored ??
	private boolean isDesiredOWLClass(OWLIndividual individual) {
		System.out.println(individual.getTypes(ontology).toString());
		System.out.println(individual.getTypes(ontology).toString()
				.contains("<http://www.foo.com/fcm#Contact>"));
		return true;
	}

	/**
	 * Method to write all the data properties as column names in CSV file.
	 */
	private void writeColumnNames() {
		try {
			Set<OWLDataProperty> columns = ontology
					.getDataPropertiesInSignature();
			Set<String> sortedColumns = new TreeSet<String>();
			metadata = new String[columns.size() + 4];
			for (OWLDataProperty dataProperty : columns) {
				sortedColumns.add(getDataPropertyFromIRI(dataProperty
						.toString()));
			}

			metadata[0] = INDIVIDUAL_PARENT;
			metadata[1] = INDIVIDUAL_PARENT_CLASS;
			metadata[2] = INDIVIDUAL;
			metadata[3] = INDIVIDUAL_CLASS;
			int i = 4;
			for (String property : sortedColumns) {
				metadata[i] = property;
				i++;
			}

			CSVUtils.writeNext(metadata);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param dataProperties
	 */
	private void writeDataPropertiesToCSV(HashMap<String, String> dataProperties) {

		String[] row = new String[metadata.length];
		try {
			// create string array from data properties map add map if key
			// doesn't exist
			for (int i = 0; i < row.length; i++) {
				String value = dataProperties.get(metadata[i]);
				if (value == null || value == "") {
					row[i] = "";
				} else {
					row[i] = value;
				}
			}
			// write line to CSV
			CSVUtils.writeNext(row);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param dataPropertyIRI
	 * @return
	 */
	private String getDataPropertyFromIRI(String dataPropertyIRI) {
		return dataPropertyIRI.substring(dataPropertyIRI.indexOf("#") + 1,
				dataPropertyIRI.indexOf(">"));
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		OWLToCSVRecursion recurse = new OWLToCSVRecursion();
		recurse.run(args);
	}

	/**
	 * Return Version information of this class file
	 */
	public static String getVersionInfo() {
		String version = "$Revision: 1.0 $";
		return version;
	}

}
