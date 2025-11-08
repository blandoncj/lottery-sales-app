package com.blandev.lottery.backend.domain.model;

import java.math.BigDecimal;

public class LotteryTicket {
  private Long id;
  private Long drawId;
  private String number;
  private BigDecimal price;
  private TicketStatus status = TicketStatus.AVAILABLE;

  public LotteryTicket(String number, BigDecimal price, Long drawId) {
    this.number = number;
    this.price = price;
    this.drawId = drawId;
  }

  public LotteryTicket(Long id, String number, BigDecimal price, TicketStatus status, Long drawId) {
    this.id = id;
    this.number = number;
    this.price = price;
    this.status = status;
    this.drawId = drawId;
  }

  public void setAsSold() {
    this.status = TicketStatus.SOLD;
  }

  public boolean isAvailable() {
    return this.status == TicketStatus.AVAILABLE;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public TicketStatus getStatus() {
    return status;
  }

  public void setStatus(TicketStatus status) {
    this.status = status;
  }

  public Long getDrawId() {
    return drawId;
  }

  public void setDrawId(Long drawId) {
    this.drawId = drawId;
  }

}
