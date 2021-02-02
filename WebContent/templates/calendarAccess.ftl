<#include "header.ftl">
<style>
    .attribute { font-size: 10px; margin-top: 6px; margin-left: 2px; color: rgba(0,0,0,0.60);}
</style>

<form method="get" action="calendar" style="text-align: left; display: inline-grid;">
    <input type="hidden" name="action" value="viewCalendar">
    <label title="Group" for="groupid" class="attribute">group</label><br>
    <input type="number" id="groupid" name="groupid" value="0" min="0"><br>
    <label class="attribute">submit</label><br>
    <input type="submit" value="view calendar" id="vcbutton">
</form>

<#include "footer.ftl">