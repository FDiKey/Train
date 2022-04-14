<#import "../parts/common.ftlh" as c>
<@c.page>
    <a href="http://localhost:8080/admin/station-add" class="btn btn-primary"> Add new station </a>
    <br>
    <datalist>
           <#list stations as station>
               <option>
               ${station.name}
        </option>
            </#list>
    </datalist>
    <div>
    <#list stations as station>
        ${station.routeNumber}
        ${station.name}
    <a href="http://localhost:8080/admin/station-edit/${station.id}">edit</a>
    <a href="http://localhost:8080/admin/delete/${station.id}">delete</a>
    <br>
    </#list>
    </div>

</@c.page>