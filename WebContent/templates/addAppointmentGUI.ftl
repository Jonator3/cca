<#include "header.ftl">

	    <script type='text/javascript'>
	    var count = 1;
	        function addFields(){
	            // Number of inputs to create
	            var number = document.getElementById("5").value;
	            // Container <div> where dynamic content will be placed
	            var container = document.getElementById("container");
	            number = Math.max(number, 1);
	            for (let i=count;i<number;i++){
                	container.appendChild(document.createTextNode("plannedParticipant no. "+(i+1)+"_:"));
                	// Create an <input> element, set its type and name attributes
                	var input = document.createElement("input");
                	input.type = "text";
                	input.name = "member"+i;
                	console.log(input.name);
                	input.required = true;
                	container.appendChild(input);
                	// Append a line break 
                	container.appendChild(document.createElement("br"));
            	}
            	for( let i=number;i<count;i++){
            		container.removeChild(container.lastChild);//label
            		container.removeChild(container.lastChild);//input
            		container.removeChild(container.lastChild);//br
            	}
            	count = number;
        	}
    	</script>
    <style>
        label { font-size: 10px; margin-top: 6px; margin-left: 2px; color: rgba(0,0,0,0.60);}
    </style>
    
    <form method="post" action="calendar" style="text-align: left; display: inline-grid;">
    	<label for="1">name</label><br>
    	<input type="text" id="1" name="Name" required><br>
    	<label for="2">description</label><br>
    	<input type="text" id="2" name="descr" required><br>
    	<label for="3">location</label><br>
    	<input type="text" id="3" name="loc" required><br>
    	<label for="4">duration</label><br>
    	<input type="time" id="4" name="date" required><br>
    	<label for="5">number ofplanned participants including you</label><br>
    	<input type="number" id="5" name="numberOfplannedParticipants" required>
    	<button type="button" onClick="addFields()">create inputFields for PP</button>
    	<div id="container" name="ppcontainer">
    	<label for="51">your Name_______________:</label>
    	<input type="text" id="51" name="member0" required><br>
    	</div>
    	<label for="6">possible dates</label><br>
    	<input type="date" id="6" name="posDatesDate" required>
    	<input type="time" id="61" name="posDatesTime" required><br>
    	<label for="7">deadline</label><br>
    	<input type="date" id="7" name="deadline" required><br>
    	<label for="8">group</label><br>
    	<input type="number" id="8" name="group_id" value="0" min="0" required><br>
		<label>submit</label>
    	<input type="submit" value="create appointment" id="aabutton" required><br>
    	<input type="hidden" name="action" value="addAppointmentGUISubmit">
    </form>

<#include "footer.ftl">
