<#import "../parts/common.ftlh" as c>
<@c.page>
    <#if message??>
        ${message}
    </#if>
    <form action="route-add" method="post">
        <input type="text" placeholder="Enter name of route" name="routeNumber">
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button type="submit">Create route</button>
    </form>
</@c.page>