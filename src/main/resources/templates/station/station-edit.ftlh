<#import "../parts/common.ftlh" as c>

<@c.page>

    <#if message??>
        ${message}
    </#if>
    <form action="${station.id}" method="post">
        <input type="text" name="stationName" placeholder="Station name" value="${station.name}"/>
        <input type="text" name="route" placeholder="Route number" value="${station.routeNumber}"/>
<#--        <input type="text" name="previous" placeholder="Previous station" value="${station.previous}">-->
<#--        <input type="text" name="next" placeholder="Next station" value="${station.next}">-->
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button type="submit" >Update station</button>
    </form>
</@c.page>