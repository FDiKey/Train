<#import "parts/common.ftlh" as c>

<@c.page>

    <#list tickets as ticket>
        <div class="card mb-3">
            <div class="card-header">
                Train №: ${ticket.train.trainNumber}
            </div>
            <div class="card-body">
                <div class="container">
                    <div class="row g-3">
                        <div class="col-4 ml-5" >
                            Station from: <H3>${ticket.fromName}</H3>
                            <span>On station</span>
                            : ${ticket.scheduleFrom.dateOnStation}
                            at time : ${ticket.scheduleFrom.timeOnStation}
                        </div>
                        <div class="col-4 ml-5" >
                            Station to: <H3>${ticket.toName}</H3>
                            <span>On station</span>
                            : ${ticket.scheduleTo.dateOnStation}
                            at time : ${ticket.scheduleTo.timeOnStation}
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