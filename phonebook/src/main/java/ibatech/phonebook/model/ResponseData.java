package ibatech.phonebook.model;

import ibatech.phonebook.model.enumerated.OperationStatus;
import ibatech.phonebook.model.enumerated.OperationType;

public class ResponseData {

    private Long userId;
    private OperationType operationType;
    private OperationStatus operationStatus;

    public ResponseData(){}

    public ResponseData(Long userId, OperationType operationType, OperationStatus operationStatus) {
        this.userId = userId;
        this.operationType = operationType;
        this.operationStatus = operationStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
