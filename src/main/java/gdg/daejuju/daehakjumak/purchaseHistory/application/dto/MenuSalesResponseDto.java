package gdg.daejuju.daehakjumak.purchaseHistory.application.dto;

public class MenuSalesResponseDto {
    private String menu;
    private long totalSales;

    public MenuSalesResponseDto(String menu, long totalSales) {
        this.menu = menu;
        this.totalSales = totalSales;
    }

    // Getters and setters
    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public long getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(long totalSales) {
        this.totalSales = totalSales;
    }
}
