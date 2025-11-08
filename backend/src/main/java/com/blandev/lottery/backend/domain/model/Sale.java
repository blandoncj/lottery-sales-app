package com.blandev.lottery.backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
  private Long id;
  private Long customerId;
  private List<LotteryTicket> tickets = new ArrayList<>();
  private BigDecimal totalAmount;
  private LocalDateTime saleDate;

  public Sale(Long customerId, List<LotteryTicket> tickets, BigDecimal totalAmount) {
    this.customerId = customerId;
    this.tickets = tickets;
    this.totalAmount = calculateTotalAmount();
    this.saleDate = LocalDateTime.now();
  }

  public Sale(Long id, Long customerId, List<LotteryTicket> tickets, BigDecimal totalAmount,
      LocalDateTime saleDate) {
    this.id = id;
    this.customerId = customerId;
    this.tickets = tickets;
    this.totalAmount = totalAmount;
    this.saleDate = saleDate;
  }

  private BigDecimal calculateTotalAmount() {
    return tickets.stream()
        .map(LotteryTicket::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public void addTicket(LotteryTicket ticket) {
    this.tickets.add(ticket);
    this.totalAmount = calculateTotalAmount();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public List<LotteryTicket> getTickets() {
    return tickets;
  }

  public void setTickets(List<LotteryTicket> tickets) {
    this.tickets = tickets;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public LocalDateTime getSaleDate() {
    return saleDate;
  }

  public void setSaleDate(LocalDateTime saleDate) {
    this.saleDate = saleDate;
  }

}
