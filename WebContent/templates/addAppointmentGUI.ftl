<#include "header.ftl">
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
    	<label for="5">planned participants including you seperatet with ,</label><br>
    	<input type="text" id="5" name="plannedParticipants" required><br>
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
