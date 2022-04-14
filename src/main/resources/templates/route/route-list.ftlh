<#import "../parts/common.ftlh" as c>
<@c.page>
    <a href="http://localhost:8080/admin/route-add" class="btn btn-primary"> Add new route </a>
    <#list routes as route>
        <div>${route.number}
        <a href="http://localhost:8080/admin/route-edit/${route.id}">edit</a>
        <a href="http://localhost:8080/admin/route-delete/${route.id}">delete</a>
        </div>
    </#list>
</@c.page>