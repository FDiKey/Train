<#import "../parts/common.ftlh" as c>
<@c.page>
    Route Editor
    <br>
    <#if message??>
        ${message}
    </#if>
    <form action="${route.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="text" name="routeNumber" value="${route.number}">
        <button type="submit">Save</button>
        <#list route.stations as station>
            <br>
            ${station.name}
        </#list>
    </form>
</@c.page>