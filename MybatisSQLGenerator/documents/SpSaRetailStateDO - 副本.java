package com.suneee.scn.sale.model.dbo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SpSaRetailStateDO implements Serializable {

	private static final long serialVersionUID = -3083265009716070310L;

	private String tid;

    private Long enterpriseid;
    
    private Long billtype;

    /**
     * 退款状态 1：无退款 2零售单完成部分退款 3零售全部退款
     */
	private Long refundtype;

    /**
     * 退货状态 1：无退货 2零售单部分退货 3零售单全部退货
     */
    private Long regoodtype;
    
    private Long departmentid;

    private String customer;

    private Long salesmanid;

    private String salesman;

    private Date gatherdate;

    private Long inputmanid;

    private String inputmanname;

    /**
     * 零售金额 -是计算完优惠之后的金额
     */
    private BigDecimal retailmoney;
    
    private Long bookmanid;

    private String bookmanname;

    private Long rtgatherno;
    
    private Long vipid;
    
    private Long province;

    private Long city;

    private Long region;

    private Long community;

    private String address;

    private String wholeaddress;

    private String telephone;
    /**
     * 商城来源单号 线上线下均存在
     */
    private String billno;
    /**
     * 折扣金额
     */
    private BigDecimal discount;
    /**
     * 发货状态 0=未发货、1=配送中、2=已发货
     */
    private Short shippingstatus;
    /**
     * 订单来源：1=商城订单、2=线下订单
     */
    private String billsource;
    
    private Date deliverytime;

    /**
     * 线下订单单号，商城订单这个字段没有值
     */
    private String storeoderno;

    /**
     * 开发票状态 0-未开发票 1-部分开票 2-已开发票
     */
  	private Integer invoicestatus;
  	
  	private BigDecimal cashin;

	private BigDecimal cashout;
	
	private BigDecimal lackgoodsmoney;   //缺货金额

    /**
     * 退款金额：此字段仅存放售后退款的退款金额
     */
	private BigDecimal refundmoney;
    /**
     * 退货金额
     */
	private BigDecimal regoodmoney;

    /**
     * 实际零售金额 （零售金额-优惠总金额-退款金额-退货金额）
     */
	private BigDecimal retailtotalmoney;
    /**
     * 订单的总优惠金额
     */
	private BigDecimal orderdiscountall;
    /**
     * 成本总金额
     */
	private BigDecimal costtotalmoney;
    /**
     * 网格区域，用于记录用户的配送区域
     */
	private String latticeName;
    /**
     * 班次号，pos端会传
     */
	private String shiftNo;
	
    private List<SpSaRetailDtlStateDO> spSaRetailDtlStateDO;
	
	/**
     * 2018.06.07 积分额：商城积分订单存储积分
     */
    private BigDecimal integralAmount;

    /**
     * 2018.06.27 档口单据类型，数据字典关键字为SP_SA_STALL_BILLTYPE，0 线上堂食、1 线下堂食、2 线上配送、3 称重加工、4 线下购买
     * 一般为0或1的订单仅包含菜品，不推送wms
     */
    private Integer stallBillType;
    
    private Date createDate;
	/**
	 * 2018.10.22 小区
	 */
	private String residence;
	
	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public Integer getStallBillType() {
        return stallBillType;
    }

    public void setStallBillType(Integer stallBillType) {
        this.stallBillType = stallBillType;
    }

    public BigDecimal getIntegralAmount() {
        return integralAmount;
    }

    public void setIntegralAmount(BigDecimal integralAmount) {
        this.integralAmount = integralAmount;
    }

    public String getLatticeName() {
        return latticeName;
    }

    public void setLatticeName(String latticeName) {
        this.latticeName = latticeName;
    }

    public BigDecimal getOrderdiscountall() {
		return orderdiscountall;
	}

	public void setOrderdiscountall(BigDecimal orderdiscountall) {
		this.orderdiscountall = orderdiscountall;
	}

	public BigDecimal getCashin() {
		return cashin;
	}

	public void setCashin(BigDecimal cashin) {
		this.cashin = cashin;
	}

	public BigDecimal getCashout() {
		return cashout;
	}

	public void setCashout(BigDecimal cashout) {
		this.cashout = cashout;
	}

	public Integer getInvoicestatus() {
		return invoicestatus;
	}


	public void setInvoicestatus(Integer invoicestatus) {
		this.invoicestatus = invoicestatus;
	}


	public String getStoreoderno() {
		return storeoderno;
	}

	public void setStoreoderno(String storeoderno) {
		this.storeoderno = storeoderno;
	}

	public BigDecimal getRegoodmoney() {
		return regoodmoney;
	}

	public void setRegoodmoney(BigDecimal regoodmoney) {
		this.regoodmoney = regoodmoney;
	}

	public BigDecimal getRefundmoney() {
		return refundmoney;
	}

	public void setRefundmoney(BigDecimal refundmoney) {
		this.refundmoney = refundmoney;
	}


	public Date getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(Date deliverytime) {
		this.deliverytime = deliverytime;
	}


    public BigDecimal getCosttotalmoney() {
		return costtotalmoney;
	}

	public void setCosttotalmoney(BigDecimal costtotalmoney) {
		this.costtotalmoney = costtotalmoney;
	}

    public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    public Long getRefundtype() {
        return refundtype;
    }

    public void setRefundtype(Long refundtype) {
        this.refundtype = refundtype;
    }

    public Long getBilltype() {
        return billtype;
    }

    public void setBilltype(Long billtype) {
        this.billtype = billtype;
    }

    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public Long getSalesmanid() {
        return salesmanid;
    }

    public void setSalesmanid(Long salesmanid) {
        this.salesmanid = salesmanid;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
    }

    public Date getGatherdate() {
        return gatherdate;
    }

    public void setGatherdate(Date gatherdate) {
        this.gatherdate = gatherdate;
    }

    public Long getInputmanid() {
        return inputmanid;
    }

    public void setInputmanid(Long inputmanid) {
        this.inputmanid = inputmanid;
    }

    public String getInputmanname() {
        return inputmanname;
    }

    public void setInputmanname(String inputmanname) {
        this.inputmanname = inputmanname == null ? null : inputmanname.trim();
    }

    public BigDecimal getRetailmoney() {
        return retailmoney;
    }

    public void setRetailmoney(BigDecimal retailmoney) {
        this.retailmoney = retailmoney;
    }

    public Long getBookmanid() {
        return bookmanid;
    }

    public void setBookmanid(Long bookmanid) {
        this.bookmanid = bookmanid;
    }

    public String getBookmanname() {
        return bookmanname;
    }

    public void setBookmanname(String bookmanname) {
        this.bookmanname = bookmanname == null ? null : bookmanname.trim();
    }

    public Long getRtgatherno() {
        return rtgatherno;
    }

    public void setRtgatherno(Long rtgatherno) {
        this.rtgatherno = rtgatherno;
    }

    public Long getVipid() {
        return vipid;
    }

    public void setVipid(Long vipid) {
        this.vipid = vipid;
    }


    public BigDecimal getRetailtotalmoney() {
        return retailtotalmoney;
    }

    public void setRetailtotalmoney(BigDecimal retailtotalmoney) {
        this.retailtotalmoney = retailtotalmoney;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public Long getCommunity() {
        return community;
    }

    public void setCommunity(Long community) {
        this.community = community;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getWholeaddress() {
        return wholeaddress;
    }

    public void setWholeaddress(String wholeaddress) {
        this.wholeaddress = wholeaddress == null ? null : wholeaddress.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	

	public Short getShippingstatus() {
		return shippingstatus;
	}

	public void setShippingstatus(Short shippingstatus) {
		this.shippingstatus = shippingstatus;
	}


	public BigDecimal getLackgoodsmoney() {
		return lackgoodsmoney;
	}

	public void setLackgoodsmoney(BigDecimal lackgoodsmoney) {
		this.lackgoodsmoney = lackgoodsmoney;
	}

    public String getShiftNo() {
        return shiftNo;
    }

    public void setShiftNo(String shiftNo) {
        this.shiftNo = shiftNo;
    }
    
    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBillsource() {
		return billsource;
	}

	public void setBillsource(String billsource) {
		this.billsource = billsource;
	}

	public Long getRegoodtype() {
		return regoodtype;
	}

	public void setRegoodtype(Long regoodtype) {
		this.regoodtype = regoodtype;
	}

	public List<SpSaRetailDtlStateDO> getSpSaRetailDtlStateDO() {
        return spSaRetailDtlStateDO;
    }

    public void setSpSaRetailDtlStateDO(List<SpSaRetailDtlStateDO> spSaRetailDtlStateDO) {
        this.spSaRetailDtlStateDO = spSaRetailDtlStateDO;
    }

    @Override
    public String toString() {
        return "SpSaRetailStateDO{" +
                "tid=" + tid +
                ", enterpriseid=" + enterpriseid +
                ", billtype=" + billtype +
                ", refundtype=" + refundtype +
                ", regoodtype=" + regoodtype +
                ", departmentid=" + departmentid +
                ", customer='" + customer + '\'' +
                ", salesmanid=" + salesmanid +
                ", salesman='" + salesman + '\'' +
                ", gatherdate=" + gatherdate +
                ", inputmanid=" + inputmanid +
                ", inputmanname='" + inputmanname + '\'' +
                ", retailmoney=" + retailmoney +
                ", bookmanid=" + bookmanid +
                ", bookmanname='" + bookmanname + '\'' +
                ", rtgatherno=" + rtgatherno +
                ", vipid=" + vipid +
                ", province=" + province +
                ", city=" + city +
                ", region=" + region +
                ", community=" + community +
                ", address='" + address + '\'' +
                ", wholeaddress='" + wholeaddress + '\'' +
                ", telephone='" + telephone + '\'' +
                ", billno='" + billno + '\'' +
                ", discount=" + discount +
                ", shippingstatus=" + shippingstatus +
                ", billsource='" + billsource + '\'' +
                ", deliverytime=" + deliverytime +
                ", storeoderno='" + storeoderno + '\'' +
                ", invoicestatus=" + invoicestatus +
                ", cashin=" + cashin +
                ", cashout=" + cashout +
                ", lackgoodsmoney=" + lackgoodsmoney +
                ", refundmoney=" + refundmoney +
                ", regoodmoney=" + regoodmoney +
                ", retailtotalmoney=" + retailtotalmoney +
                ", orderdiscountall=" + orderdiscountall +
                ", costtotalmoney=" + costtotalmoney +
                ", latticeName='" + latticeName + '\'' +
                ", shiftNo='" + shiftNo + '\'' +
                ", spSaRetailDtlStateDO=" + spSaRetailDtlStateDO +
                ", integralAmount=" + integralAmount +
                ", stallBillType=" + stallBillType +
                ", createDate=" + createDate +
                ", residence='" + residence + '\'' +
                '}';
    }
}