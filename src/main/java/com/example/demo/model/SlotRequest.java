package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(indexName = "sr", type = "slotRequest")
@Data
public class SlotRequest implements Comparable<SlotRequest> {

    @Id
    private String id;
    private long startTime;
    private long endTime;
    private String dateOfRequest;
    private String bookingDate;
    private String approvedDate;
    @Getter
    @Setter
    private String entityId;
    @Getter
    @Setter
    private String userEmail;
    @Getter
    @Setter
    private SlotStatus status = SlotStatus.SUBMITTED;
    private String entityName;
    
    @Override
    public int compareTo(SlotRequest slotRequest) {
        String datediff=((SlotRequest)slotRequest).getBookingDate();
        if(this.getBookingDate().compareTo(datediff) != 0) {
           // return this.getBookingDate().compareTo(datediff);
            return datediff.compareTo(this.getBookingDate());
        } else {
            //return (int)(this.getStartTime() - ((SlotRequest)slotRequest).getStartTime());
            return (int)(((SlotRequest)slotRequest).getStartTime() - this.getStartTime());
        }
    }

}
