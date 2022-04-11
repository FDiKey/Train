<#import "../parts/common.ftlh" as c>
<@c.page>
    <#if message??>
        ${message}
    </#if>
    <form action="station-add" method="post">
        <input type="text" name="stationName" placeholder="Station name"/>
        <input type="text" name="route" placeholder="Route number"/>
        <input type="text" name="previous" placeholder="Previous station">
        <input type="text" name="next" placeholder="Next station">
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button type="submit" >Create new station</button>
    </form>
</@c.page>