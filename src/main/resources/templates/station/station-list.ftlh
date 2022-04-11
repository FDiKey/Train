<#import "../parts/common.ftlh" as c>
<@c.page>
    <a href="http://localhost:8080/admin/station-add" class="btn btn-primary"> Add new station </a>
    <#list stations as station>
        ${station.routeNumber}
        ${station.name}
    <a href="./station-edit/${station.id}">edit</a>
    <a href="/delete">delete</a>
    <br>
    </#list>

</@c.page>