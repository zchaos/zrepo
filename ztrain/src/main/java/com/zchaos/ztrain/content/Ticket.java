package com.zchaos.ztrain.content;

import java.util.Date;

import com.zchaos.ztrain.consts.Seat;

/**
 * 车票信息
 */
public class Ticket {
	private Date date;//发车日期和时间

	private Contact contact;//乘车人

	private Address startAddress;//出发地

	private Address endAddress;//目的地

	private Seat seat;//座位

	private Train train;//列车

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Address getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(Address startAddress) {
		this.startAddress = startAddress;
	}

	public Address getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(Address endAddress) {
		this.endAddress = endAddress;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
}
