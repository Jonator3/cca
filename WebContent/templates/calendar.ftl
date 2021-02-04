<#include "header.ftl">
    <script src="https://xyz.ddnss.de/calendar.js"></script>
    <style>
        .attribute { font-size: 10px; margin-top: 6px; margin-left: 2px; color: rgba(0,0,0,0.60); }
        .id {font-size: 10px; float: right; color: rgba(0,0,0,0.60); position: absolute; right: 20px; margin-top: -14px; }
        .popup { color: rgba(0,0,0,0.87) !important; }
        .popup h3 { margin: 4px; }
    </style>

    <div class="calendar" id="calendar" style="width: 100%;">
        <#list appointments as a>

            <#list a.getDates() as pd>
                <div class="appointment"
                     data-day="${pd.getDate().getDay()}"
                     data-month="${pd.getDate().getMonth()}"
                     data-year="${pd.getDate().getYear()}"
                    style="<#if !a.isFinal()>background-color: #2196F3;</#if>">

                    ${a.getName()}

                    <div class="popup" style="width: 300px; font-size: 15px;">
                        <h3>${a.getName()}</h3>
                        <#if !a.isFinal()>
                            <div style="font-size: 10px; margin-left: 10px;">preliminary date</div>
                        </#if>
                        <div class="id">${a.getId()}</div>
                        <hr>

                        <div class="attribute">description</div>
                        ${a.getDescription()}

                        <div class="attribute">location</div>
                        ${a.getLocation()}

                        <div class="attribute">time, duration</div>
                        ${pd.getDate().toTimeString()},
                        ${a.getDuration().toDisplayString()}

                        <div class="attribute">participants</div>
                        <#list pd.getPossible_participants() as pp>
                            ${pp},
                        </#list>

                        <div class="attribute">answer appointment</div>
                        <form method="post" action="calendar">
                            <input type="hidden" value="selectDate" name="action">
                            <input type="hidden" name="AId" value="${a.getId()}" required>
                            <input type="hidden" name="SelectedDate" value="${pd.getDate().toString()}" required>
                            <input type="text" name="MyName" placeholder="Max Mustermann" required>
                            <input type="submit" value="Participate">
                        </form>
                    </div>
                </div>
            </#list>
        </#list>

        <div class="popup">
            <form method="post" action="calendar" style="display: inline-grid;">
                <input type="hidden" value="suggestDate" name="action">
                <div class="attribute">appointment</div>
                <select name="AId" required>
                    <#list appointments as a>
                        <option value="${a.getId()}">${a.getName()}</option>
                    </#list>
                </select><br>
                <div class="attribute">date</div>
                <input type="date" class="autofilldate" name="date" readonly="readonly" required>
                <div class="attribute">time</div>
                <input type="time" value="12:00" name="time" required>
                <div class="attribute">name</div>
                <input type="text" name="MyName" placeholder="Max Mustermann" required>
                <div class="attribute">submit</div>
                <input type="submit" value="suggest date">
            </form>
        </div>

    </div>


<#include "footer.ftl">