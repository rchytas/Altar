<?xml version="1.0" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Whole Numbers between 1 and 100</h2>
				<table border="1">
					<tr>
						<th>Odd Numbers</th>
						<th>Even Numbers</th>
					</tr>
					<xsl:for-each select="Whole_Numbers/Number[Parity='odd']">
						<tr>
							<td>
								<xsl:value-of select="Value" />
							</td>
							<td>
								<xsl:value-of select="following-sibling::Number[1]/Value" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
