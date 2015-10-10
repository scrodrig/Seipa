/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ronny
 */
@Entity
@Table(name = "DIMTIME", catalog = "", schema = "BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dimtime.findAll", query = "SELECT d FROM Dimtime d"),
    @NamedQuery(name = "Dimtime.findByFecha", query = "SELECT d FROM Dimtime d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "Dimtime.findByDatakey", query = "SELECT d FROM Dimtime d WHERE d.datakey = :datakey"),
    @NamedQuery(name = "Dimtime.findByFechahora", query = "SELECT d FROM Dimtime d WHERE d.fechahora = :fechahora"),
    @NamedQuery(name = "Dimtime.findByAnio", query = "SELECT d FROM Dimtime d WHERE d.anio = :anio"),
    @NamedQuery(name = "Dimtime.findByTrimestre", query = "SELECT d FROM Dimtime d WHERE d.trimestre = :trimestre"),
    @NamedQuery(name = "Dimtime.findByYeartrimestre", query = "SELECT d FROM Dimtime d WHERE d.yeartrimestre = :yeartrimestre"),
    @NamedQuery(name = "Dimtime.findByQuarter", query = "SELECT d FROM Dimtime d WHERE d.quarter = :quarter"),
    @NamedQuery(name = "Dimtime.findByYearquarter", query = "SELECT d FROM Dimtime d WHERE d.yearquarter = :yearquarter"),
    @NamedQuery(name = "Dimtime.findByMes", query = "SELECT d FROM Dimtime d WHERE d.mes = :mes"),
    @NamedQuery(name = "Dimtime.findByYearmonth", query = "SELECT d FROM Dimtime d WHERE d.yearmonth = :yearmonth"),
    @NamedQuery(name = "Dimtime.findByMonthname", query = "SELECT d FROM Dimtime d WHERE d.monthname = :monthname"),
    @NamedQuery(name = "Dimtime.findByShortmonthname", query = "SELECT d FROM Dimtime d WHERE d.shortmonthname = :shortmonthname"),
    @NamedQuery(name = "Dimtime.findByWeek", query = "SELECT d FROM Dimtime d WHERE d.week = :week"),
    @NamedQuery(name = "Dimtime.findByDayinyear", query = "SELECT d FROM Dimtime d WHERE d.dayinyear = :dayinyear"),
    @NamedQuery(name = "Dimtime.findByDayinmonth", query = "SELECT d FROM Dimtime d WHERE d.dayinmonth = :dayinmonth"),
    @NamedQuery(name = "Dimtime.findByDayname", query = "SELECT d FROM Dimtime d WHERE d.dayname = :dayname"),
    @NamedQuery(name = "Dimtime.findByShortdayname", query = "SELECT d FROM Dimtime d WHERE d.shortdayname = :shortdayname"),
    @NamedQuery(name = "Dimtime.findByLastdayinmonth", query = "SELECT d FROM Dimtime d WHERE d.lastdayinmonth = :lastdayinmonth"),
    @NamedQuery(name = "Dimtime.findByMonthyear", query = "SELECT d FROM Dimtime d WHERE d.monthyear = :monthyear"),
    @NamedQuery(name = "Dimtime.findByHoliday", query = "SELECT d FROM Dimtime d WHERE d.holiday = :holiday"),
    @NamedQuery(name = "Dimtime.findByDayinmonthOrd", query = "SELECT d FROM Dimtime d WHERE d.dayinmonthOrd = :dayinmonthOrd"),
    @NamedQuery(name = "Dimtime.findByMesOrd", query = "SELECT d FROM Dimtime d WHERE d.mesOrd = :mesOrd"),
    @NamedQuery(name = "Dimtime.findBySemanaOrd", query = "SELECT d FROM Dimtime d WHERE d.semanaOrd = :semanaOrd")})
public class Dimtime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "DATAKEY")
    private BigInteger datakey;
    @Column(name = "FECHAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Column(name = "ANIO")
    private BigInteger anio;
    @Column(name = "TRIMESTRE")
    private BigInteger trimestre;
    @Size(max = 7)
    @Column(name = "YEARTRIMESTRE", length = 7)
    private String yeartrimestre;
    @Column(name = "QUARTER")
    private BigInteger quarter;
    @Size(max = 7)
    @Column(name = "YEARQUARTER", length = 7)
    private String yearquarter;
    @Column(name = "MES")
    private BigInteger mes;
    @Column(name = "YEARMONTH")
    private BigInteger yearmonth;
    @Size(max = 10)
    @Column(name = "MONTHNAME", length = 10)
    private String monthname;
    @Size(max = 3)
    @Column(name = "SHORTMONTHNAME", length = 3)
    private String shortmonthname;
    @Column(name = "WEEK")
    private BigInteger week;
    @Column(name = "DAYINYEAR")
    private BigInteger dayinyear;
    @Column(name = "DAYINMONTH")
    private BigInteger dayinmonth;
    @Size(max = 9)
    @Column(name = "DAYNAME", length = 9)
    private String dayname;
    @Size(max = 3)
    @Column(name = "SHORTDAYNAME", length = 3)
    private String shortdayname;
    @Column(name = "LASTDAYINMONTH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastdayinmonth;
    @Size(max = 9)
    @Column(name = "MONTHYEAR", length = 9)
    private String monthyear;
    @Column(name = "HOLIDAY")
    private BigInteger holiday;
    @Size(max = 2)
    @Column(name = "DAYINMONTH_ORD", length = 2)
    private String dayinmonthOrd;
    @Size(max = 2)
    @Column(name = "MES_ORD", length = 2)
    private String mesOrd;
    @Size(max = 2)
    @Column(name = "SEMANA_ORD", length = 2)
    private String semanaOrd;

    public Dimtime() {
    }

    public Dimtime(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getDatakey() {
        return datakey;
    }

    public void setDatakey(BigInteger datakey) {
        this.datakey = datakey;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public BigInteger getAnio() {
        return anio;
    }

    public void setAnio(BigInteger anio) {
        this.anio = anio;
    }

    public BigInteger getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(BigInteger trimestre) {
        this.trimestre = trimestre;
    }

    public String getYeartrimestre() {
        return yeartrimestre;
    }

    public void setYeartrimestre(String yeartrimestre) {
        this.yeartrimestre = yeartrimestre;
    }

    public BigInteger getQuarter() {
        return quarter;
    }

    public void setQuarter(BigInteger quarter) {
        this.quarter = quarter;
    }

    public String getYearquarter() {
        return yearquarter;
    }

    public void setYearquarter(String yearquarter) {
        this.yearquarter = yearquarter;
    }

    public BigInteger getMes() {
        return mes;
    }

    public void setMes(BigInteger mes) {
        this.mes = mes;
    }

    public BigInteger getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(BigInteger yearmonth) {
        this.yearmonth = yearmonth;
    }

    public String getMonthname() {
        return monthname;
    }

    public void setMonthname(String monthname) {
        this.monthname = monthname;
    }

    public String getShortmonthname() {
        return shortmonthname;
    }

    public void setShortmonthname(String shortmonthname) {
        this.shortmonthname = shortmonthname;
    }

    public BigInteger getWeek() {
        return week;
    }

    public void setWeek(BigInteger week) {
        this.week = week;
    }

    public BigInteger getDayinyear() {
        return dayinyear;
    }

    public void setDayinyear(BigInteger dayinyear) {
        this.dayinyear = dayinyear;
    }

    public BigInteger getDayinmonth() {
        return dayinmonth;
    }

    public void setDayinmonth(BigInteger dayinmonth) {
        this.dayinmonth = dayinmonth;
    }

    public String getDayname() {
        return dayname;
    }

    public void setDayname(String dayname) {
        this.dayname = dayname;
    }

    public String getShortdayname() {
        return shortdayname;
    }

    public void setShortdayname(String shortdayname) {
        this.shortdayname = shortdayname;
    }

    public Date getLastdayinmonth() {
        return lastdayinmonth;
    }

    public void setLastdayinmonth(Date lastdayinmonth) {
        this.lastdayinmonth = lastdayinmonth;
    }

    public String getMonthyear() {
        return monthyear;
    }

    public void setMonthyear(String monthyear) {
        this.monthyear = monthyear;
    }

    public BigInteger getHoliday() {
        return holiday;
    }

    public void setHoliday(BigInteger holiday) {
        this.holiday = holiday;
    }

    public String getDayinmonthOrd() {
        return dayinmonthOrd;
    }

    public void setDayinmonthOrd(String dayinmonthOrd) {
        this.dayinmonthOrd = dayinmonthOrd;
    }

    public String getMesOrd() {
        return mesOrd;
    }

    public void setMesOrd(String mesOrd) {
        this.mesOrd = mesOrd;
    }

    public String getSemanaOrd() {
        return semanaOrd;
    }

    public void setSemanaOrd(String semanaOrd) {
        this.semanaOrd = semanaOrd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dimtime)) {
            return false;
        }
        Dimtime other = (Dimtime) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.seipa.model.Dimtime[ fecha=" + fecha + " ]";
    }
    
}
