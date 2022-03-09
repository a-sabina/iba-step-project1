package ibatech.phonebook.model;

import ibatech.phonebook.model.enumerated.OperationStatus;
import ibatech.phonebook.model.enumerated.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData {

    private Long userId;
    private OperationType operationType;
    private OperationStatus operationStatus;
}
