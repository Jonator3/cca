<#include "header.ftl">
    <script src="https://xyz.ddnss.de/calendar.js"></script>

    <div class="calendar">
        <#list appointments as a>
            <#list a.getDates() as pd>
                <div class="appointment" style="width: 100%;"
                     data-day="${pd.getDate().getDay()}"
                     data-month="${pd.getDate().getMonth()}"
                     data-year="${pd.getDate().getYear()}">
                    ${a.getName()}
                </div>
            </#list>
        </#list>
    </div>


<#include "footer.ftl">