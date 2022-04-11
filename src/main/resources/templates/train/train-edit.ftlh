<#import "../parts/common.ftlh" as c>

<@c.page>
    <form action="${train.id}" method="post">
        <div><input type="text" name="trainNumber" placeholder="Enter train number" value="${train.trainNumber}"/></div>
        <div>
            <select name="route">
            <#list routes as route>
                    <option>${route.number}</option>
            </#list>
            </select>
        </div>
        <div><input type="number" name="seatCount" placeholder="Enter count of seats" value="${train.seatCount}"/></div>
        <div><input type="datetime-local" name="dateStart" value="${train.start}"/></div>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>

        <div><button type="submit">Update</button></div>

    </form>
</@c.page>