package com.zchaos.ztrain.content;

import java.util.Date;
import java.util.List;

import com.zchaos.ztrain.consts.Seat;
import com.zchaos.ztrain.consts.TrainType;

/**
 * 列车信息
 */
public class Train {
	private String trainNum;//车次

	private List<Address> crossAddress;//途径的所有站，第一个为起点站，最后一个为终点站

	private TrainType type;//列车类型

	private List<Seat> seats;//座位类型

	private List<Date> dates;//经过每一站的时间，与crossAddress一一对应

	public String getTrainNum() {
		return trainNum;
	}

	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}

	public List<Address> getCrossAddress() {
		return crossAddress;
	}

	public void setCrossAddress(List<Address> crossAddress) {
		this.crossAddress = crossAddress;
	}

	public TrainType getType() {
		return type;
	}

	public void setType(TrainType type) {
		this.type = type;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
}
