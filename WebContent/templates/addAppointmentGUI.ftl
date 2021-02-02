<#include "header.ftl">
    <style>
        label { font-size: 10px; margin-top: 6px; margin-left: 2px; color: rgba(0,0,0,0.60);}
    </style>
    
    <form method="post" action="calendar" style="text-align: left; display: inline-grid;">
    	<label for="1">name</label><br>
    	<input type="text" id="1" name="Name"><br>
    	<label for="2">description</label><br>
    	<input type="text" id="2" name="descr"><br>
    	<label for="3">location</label><br>
    	<input type="text" id="3" name="loc"><br>
    	<label for="4">duration</label><br>
    	<input type="time" id="4" name="date"><br>
    	<label for="5">planned participants</label><br>
    	<input type="text" id="5" name="plannedParticipants"><br>
    	<label for="6">possible dates</label><br>
    	<input type="date" id="6" name="posDatesDate">
    	<input type="time" id="61" name="posDatesTime"><br>
    	<label for="7">deadline</label><br>
    	<input type="date" id="7" name="deadline"><br>
    	<label for="8">group</label><br>
    	<input type="number" id="8" name="group_id" value="0" min="0"><br>
		<label>submit</label>
    	<input type="submit" value="create appointment" id="aabutton"><br>
    	<input type="hidden" name="action" value="addAppointmentGUISubmit">
    </form>

<#include "footer.ftl">
