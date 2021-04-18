package com.edu.Web3;


import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "table")
@SessionScoped
public class TableBean implements Serializable {
    private static final long serialVersionUID=8349L;
    private final List<Entity> entities= new ArrayList<>();
    private @Getter final List<Entity> drawEntities = new ArrayList<>();
    @ManagedProperty(value = "#{database}")
    private @Setter Database database;
    private String sessionId;
    private @Getter final Check check = new Checker();


    @PostConstruct
    public void init(){
        database.getEntities(sessionId,this);
        for(Entity i : entities){
            Point originalPoint = i.getPoint();
            Point drawPoint = new Point(originalPoint.getX(),originalPoint.getY(),originalPoint.getR());
            drawEntities.add(new Entity(drawPoint,i.isHit()));
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if(session!=null)
                sessionId = session.getId();
            else sessionId = null;
        }
    }


    public List<Entity> getEntities(){
        return entities;
    }

    public void addPoint(BigDecimal x, BigDecimal y, BigDecimal r){
        Point originalPoint = new Point(x,y,r);
        Point drawPoint = new Point(x,y,r);
        Entity originalEntity = new Entity(originalPoint,check.checkPoint(originalPoint));
        Entity drawEntity = new Entity(drawPoint,check.checkPoint(drawPoint));
        entities.add(originalEntity);
        draw(r);
        drawEntities.add(drawEntity);
        database.addEntity(sessionId,originalEntity);
    }

    public void draw(BigDecimal r){
        for(TableBean.Entity i : drawEntities){
            i.getPoint().setR(r);
            i.setHit(check.checkPoint(i.getPoint()));
        }
    }

    @Data
    public class Entity{
        @NonNull
        private Point point;
        @NonNull
        private boolean hit;
    }
}
