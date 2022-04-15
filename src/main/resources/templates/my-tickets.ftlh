<#import "parts/common.ftlh" as c>

<@c.page>

    <#list tickets as ticket>
        <div class="card mb-3">
            <div class="card-header">
                Train №: ${ticket.trainNumber}
            </div>
            <div class="card-body">
                <div class="container">
                    <div class="row g-3">
                        <div class="col-4 ml-5" >
                            Station from: <H3>${ticket.stationFrom}</H3>
                            <span>On station</span>
                            : ${ticket.dateFrom}
                            at time : ${ticket.timeFrom}
                        </div>
                        <div class="col-4 ml-5" >
                            Station to: <H3>${ticket.stationTo}</H3>
                            <span>On station</span>
                            : ${ticket.dateTo}
                            at time : ${ticket.timeTo}
                        </div>
                        <div class="col-2 mt-4">
                            <span> Seat Number: </span>
                            <b>${ticket.seatNumber}</b>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</@c.page>