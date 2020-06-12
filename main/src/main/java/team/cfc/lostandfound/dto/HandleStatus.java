package team.cfc.lostandfound.dto;

public enum HandleStatus {
    success(0), failed(1), outDate(2);

    public int code;

    private HandleStatus(int code) {
        this.code = code;
    }

}
