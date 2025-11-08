package com.blandev.lottery.backend.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LotteryDraw {
  private Long id;
  private String name;
  private LocalDateTime drawDate;
  private List<LotteryTicket> tickets = new ArrayList<>();

  public LotteryDraw(String name, LocalDateTime drawDate) {
    this.name = name;
    this.drawDate = drawDate;
  }

  public LotteryDraw(Long id, String name, LocalDateTime drawDate, List<LotteryTicket> tickets) {
    this.id = id;
    this.name = name;
    this.drawDate = drawDate;
    this.tickets = tickets;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getDrawDate() {
    return drawDate;
  }

  public void setDrawDate(LocalDateTime drawDate) {
    this.drawDate = drawDate;
  }

  public List<LotteryTicket> getTickets() {
    return tickets;
  }

  public void setTickets(List<LotteryTicket> tickets) {
    this.tickets = tickets;
  }

}
