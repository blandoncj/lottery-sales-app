package com.blandev.lottery.backend.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lottery_draws")
public class LotteryDrawEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "draw_date", nullable = false)
    private LocalDateTime drawDate;

    @OneToMany(mappedBy = "lotteryDraw", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LotteryTicketEntity> tickets = new ArrayList<>();

    public LotteryDrawEntity() {
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

    public List<LotteryTicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<LotteryTicketEntity> tickets) {
        this.tickets = tickets;
    }

}
