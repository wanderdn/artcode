package com.example.elastic;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.elasticsearch.annotations.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(indexName = "validation_errors")
public class ValidationDTO { //doc

    public final String transactionID;

    public final boolean b;
    public Integer aa;

    public ValidationDTO(
            boolean b, String transactionID
    ) {
        this.b = b;
        this.transactionID = transactionID;
    }

    @Override
    public String toString() {
        return "ValidationDTO{" +
                "transactionID='" + transactionID + '\'' +
                ", b=" + b +
                ", aa=" + aa +
                '}';
    }
}

