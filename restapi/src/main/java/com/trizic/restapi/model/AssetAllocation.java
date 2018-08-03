package com.trizic.restapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class AssetAllocation {

  /**
   * ID
   */
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="asset_seq")
  @SequenceGenerator(
      name="asset_seq",
      sequenceName="asset_sequence",
      allocationSize=20
  )
  @JsonIgnore
  private Long id;

  /**
   * The asset symbol (Required)
   */
  @NotNull(message = "The asset symbol can not be null.")
  private String symbol;

  /**
   * The percentage of the model to allocate for the symbol (Required)
   */
  @NotNull(message = "The percentage of the model can not be null.")
  private Double percentage;

  /**
   * The asset symbol (Required)
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * The asset symbol (Required)
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * The percentage of the model to allocate for the symbol (Required)
   */
  public Double getPercentage() {
    return percentage;
  }

  /**
   * The percentage of the model to allocate for the symbol (Required)
   */
  public void setPercentage(Double percentage) {
    this.percentage = percentage;
  }

  /**
   * return id
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * set id
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  public static AssetAllocation of(Long id, String symbol, Double percentage) {

    AssetAllocation assetAllocation = new AssetAllocation();
    assetAllocation.setPercentage(percentage);
    assetAllocation.setSymbol(symbol);
    assetAllocation.setId(id);

    return assetAllocation;
  }


}
