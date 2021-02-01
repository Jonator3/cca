<#include "header.ftl">
    <script src="https://xyz.ddnss.de/calendar.js"></script>
    <style>
        .attribute { font-size: 10px; margin-top: 6px; margin-left: 2px; color: rgba(0,0,0,0.60);}
        .popup { color: rgba(0,0,0,0.87) !important; }
        .popup h3 { margin: 4px; }
    </style>

    <div class="calendar" style="width: 100%;">
        <#list appointments as a>

            <#list a.getDates() as pd>
                <div class="appointment"
                     data-day="${pd.getDate().getDay()}"
                     data-month="${pd.getDate().getMonth()}"
                     data-year="${pd.getDate().getYear()}">

                    ${a.getName()}

                    <div class="popup" style="width: 300px; font-size: 15px;">
                        <h3>${a.getName()}</h3>
                        <div style="font-size: 10px; float: right; color: rgba(0,0,0,0.60); position: absolute; right: 20px;">${a.getId()}</div>
                        <div style="font-size: 10px; margin-left: 10px;">preliminary date</div>
                        <hr>

                        <div class="attribute">description</div>
                        ${a.getDescription()}

                        <div class="attribute">location</div>
                        ${a.getLocation()}

                        <div class="attribute">duration</div>
                        ${pd.getDate().toDisplayString()}

                        <div class="attribute">participants</div>
                        <#list pd.getPossible_participants() as pp>
                            ${pp},
                        </#list>

                        <div class="attribute">answer appointment</div>
                        <form method="post" action="/calendar">
                            <input type="hidden" value="selectDate" name="action">
                            <input type="hidden" name="AId" value="${a.getId()}">
                            <input type="hidden" name="SelectedDate" value="${pd.getDate().toString()}">
                            <input type="text" name="MyName">
                            <input type="submit" value="Participate"></form>
                    </div>
                </div>
            </#list>
        </#list>

        <div class="popup">
            <form method="post" action="/calendar">
                <input type="hidden" value="suggestDate" name="action">
                <div class="attribute">appointment</div>
                <select name="AId">
                    <#list appointments as a>
                        <option value="${a.getId()}">${a.getName()}</option>
                    </#list>
                </select><br>
                <div class="attribute">date</div>
                <input type="date" class="autofilldate" name="date">
                <div class="attribute">time</div>
                <input type="time" value="12:00" name="time">
                <div class="attribute">submit</div>
                <input type="submit" value="suggest date">
            </form>
        </div>

    </div>


<#include "footer.ftl">