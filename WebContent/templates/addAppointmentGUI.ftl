<#include "header.ftl">
    <style>
        .attribute { font-size: 10px; margin-top: 6px; margin-left: 2px; color: rgba(0,0,0,0.60);}
        .popup { color: rgba(0,0,0,0.87) !important; }
        .popup h3 { margin: 4px; }
    </style>
    
    <form method="post" action="/calendar">
    	<label for="1">Name: </label><br>
    	<input type="text" id="1" name="Name"><br>
    	<label for="2">description: </label><br>
    	<input type="text" id="2" name="descr"><br>
    	<label for="3">Location:</label><br>
    	<input type="text" id="3" name="loc"><br>
    	<label for="4">duration:</label><br>
    	<input type="time" id="4" name="date"><br>
    	<label for="5">Planned Participants:</label><br>
    	<input type="text" id="5" name="plannedParticipants"><br>
    	<label for="6">Possible dates:</label><br>
    	<input type="date" id="6" name="posDatesDate">
    	<input type="time" id="61" name="posDatesTime"><br>
    	<label for="7">deadline:</label><br>
    	<input type="date" id="7" name="deadline"><br>
    	<label for="8">group:</label><br>
    	<input type="number" id="8" name="group_id"><br>
    	<input type="submit" value="Submit"><br>
    	<input type="hidden" name="action" value="addAppointmentGUISubmit">
    </form>

<#include "footer.ftl">