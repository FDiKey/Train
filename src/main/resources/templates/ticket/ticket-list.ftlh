<#import "../parts/common.ftlh" as c>
<@c.page>
    <form action="" method="get">
        <#if trains??>
            <select name="trainNumber" >
            <#list trains as train>
                <option>${train.trainNumber}</option>
            </#list>
            </select>
        </#if>

        <button type="submit">Show tickets</button>
        <#if tickets??>
        <#list tickets as ticket>
            <br>
            From : ${ticket.scheduleFrom.stationName}
            To : ${ticket.scheduleTo.stationName}
            ${ticket.seatNumber}
            Passenger : ${ticket.passenger.name} ${ticket.passenger.surname}
        </#list>
        </#if>
        <#if message??>
            ${message}
        </#if>
    </form>
</@c.page>