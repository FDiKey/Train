<#import "../parts/common.ftlh" as c>
<@c.page>

    <#if message??>
        ${message}
    </#if>

    <form action="train-add" method="post">
        <div><input type="text" name="trainNumber" placeholder="Enter train number"/></div>
        <div>
            <select name="route">
                <#if routes??>
            <#list routes as route>
                <option selected="selected">${route.number}</option>
            </#list>
                </#if>
            </select>
        </div>
        <div><input type="number" name="seatCount" placeholder="Enter count of seats"/></div>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <div>
            <input type="date" name="dateStart"/>
            <input type="time" name="timeStart"/>
        </div>

        <div><button type="submit">Add new train</button></div>

    </form>
</@c.page>