<!doctype html>
<HTML>
<HEAD>

    <SCRIPT language="javascript">
        function addRow(tableID)
		{
			var table = document.getElementById(tableID);
			//var tmp=0;
			temp++;
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
			//tmp=(rowCount+1);
			var cell2 = row.insertCell(0);
			cell2.innerHTML = "<h3> Test case " + temp + ": </h3>";
			
			rowCount = table.rows.length;
            row = table.insertRow(rowCount);
			//tmp=(rowCount+1);
			var cell4 = row.insertCell(0);
			cell4.innerHTML = "Input:";
			
			rowCount = table.rows.length;
            row = table.insertRow(rowCount);
			var cell1 = row.insertCell(0);
			var element1 = document.createElement("textarea");
            element1.rows = 5;
			element1.cols = 120;	
			element1.name = "input" + temp;
            cell1.appendChild(element1);
			
			rowCount = table.rows.length;
            row = table.insertRow(rowCount);
			//tmp=(rowCount+1);
			var cell6 = row.insertCell(0);
			cell6.innerHTML = "Outline:";
			
			rowCount = table.rows.length;
            row = table.insertRow(rowCount);
			var cell3 = row.insertCell(0);
			var element3 = document.createElement("textarea");
            element3.rows = 5;
			element3.cols = 120;	
			element3.name = "outline" + temp;
            cell3.appendChild(element3);			
        }
 
    </SCRIPT>
</HEAD>
<style type="text/css">
h1 {color: white}
</style>
<BODY>
<script  type="text/javascript">
	temp=1;
</script>
<table>
	<tr>
		<td style="background-color:#0066FF; width:1000px; height:10px">
			<h1>Test</h1>
		</td>
	</tr>
	</table>

	<form action="" method="get">
	Purpose: <br/>
	<textarea rows="5" cols="120" name="purpose"></textarea><br/>
	<h3>Test case 1:</h3><br/>
	Input:
	<br/>
    <TABLE id="dataTable">
		<TR>  
			<TD> <textarea rows="5" cols="120" name="input1"></textarea></TD>
        </TR>
		<TR>  
			<TD>Outline:<br/> <textarea rows="5" cols="120" name="outline1"></textarea></TD>
        </TR>
    </TABLE>
	
	    <INPUT type="button" value="Add test case" onclick="addRow('dataTable')" />
<br/> 

<input type="submit" value="Submit" />
	</form>
 
</BODY>
</HTML>