<#import "parts/common.ftlh" as c>
<@c.page>
<div>
    <a href="/admin/route-list">Routes</a>
    <a href="/admin/station-list">Stations</a>
    <a href="/admin/train-list">Trains</a>
    <a href="/admin/ticket-list">Tickets</a>

    <form action="refresh-schedule" method="get">
        <button type="submit">refresh schedule</button>
    </form>
</div>
</@c.page>