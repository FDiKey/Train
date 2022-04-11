<#import "../parts/common.ftlh" as c>
<@c.page>
    <#list stations as station>
        ${station.routeNumber}
        ${station.name}
    <a href="./station-edit/${station.id}">edit</a>
    <a href="/delete">delete</a>
    <br>
    </#list>
</@c.page>