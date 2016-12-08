package dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Class for trade information for OptionalTrade.
 */
//Don't use validation annotation here. Use custom validation
public class OptionTradeProduct extends TradeProduct {
    private String style;
    private String strategy;
    private Date deliveryDate;
    private Date expiryDate;
    private Date excerciseStartDate;
    private Currency payCcy;
    private BigDecimal premium;
    private Currency premiumCcy;
    private Currency premiumType;
    private Date premiumDate;

    public OptionTradeProduct() {
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExcerciseStartDate() {
        return excerciseStartDate;
    }

    public void setExcerciseStartDate(Date excerciseStartDate) {
        this.excerciseStartDate = excerciseStartDate;
    }

    public Currency getPayCcy() {
        return payCcy;
    }

    public void setPayCcy(Currency payCcy) {
        this.payCcy = payCcy;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public Currency getPremiumCcy() {
        return premiumCcy;
    }

    public void setPremiumCcy(Currency premiumCcy) {
        this.premiumCcy = premiumCcy;
    }

    public Currency getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(Currency premiumType) {
        this.premiumType = premiumType;
    }

    public Date getPremiumDate() {
        return premiumDate;
    }

    public void setPremiumDate(Date premiumDate) {
        this.premiumDate = premiumDate;
    }
}
