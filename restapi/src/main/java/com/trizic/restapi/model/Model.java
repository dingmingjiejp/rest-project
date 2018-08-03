package com.trizic.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Model {

  /**
   * ID
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_seq")
  @SequenceGenerator(
      name = "model_seq",
      sequenceName = "model_sequence",
      allocationSize = 20
  )
  @JsonProperty("guid")
  private Long id;

  /**
   * advisor ID
   */
  private String advisorId;

  /**
   * Unique Name for the model (Required)
   */
  @NotNull(message = "name.invalid")
  private String name;

  /**
   * Description of the model (Required)
   */
  @NotNull(message = "description.invalid")
  private String description;

  /**
   * Percentage of cash to hold in the model (Required)
   */
  @NotNull(message = "cashHoldingPercentage.invalid")
  private Integer cashHoldingPercentage;

  /**
   * Percentage of drift from target allocation of assets (Required)
   */
  @NotNull(message = "driftPercentage.invalid")
  private Integer driftPercentage;

  /**
   * Type of model (Required)
   */
  @NotNull(message = "modelType.invalid")
  private Model.ModelType modelType;

  /**
   * Frequency to rebalance model (Required)
   */
  @NotNull(message = "rebalanceFrequency.invalid")
  private Model.RebalanceFrequency rebalanceFrequency;

  /**
   * Asset Allocations (Required)
   */
  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  @NotNull(message = "assetAllocations.invalid")
  private List<AssetAllocation> assetAllocations = null;

  /**
   * Unique Name for the model (Required)
   */
  public String getName() {
    return name;
  }

  /**
   * Unique Name for the model (Required)
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Description of the model (Required)
   */
  public String getDescription() {
    return description;
  }

  /**
   * Description of the model (Required)
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Percentage of cash to hold in the model (Required)
   */
  public Integer getCashHoldingPercentage() {
    return cashHoldingPercentage;
  }

  /**
   * Percentage of cash to hold in the model (Required)
   */
  public void setCashHoldingPercentage(Integer cashHoldingPercentage) {
    this.cashHoldingPercentage = cashHoldingPercentage;
  }

  /**
   * Percentage of drift from target allocation of assets (Required)
   */
  public Integer getDriftPercentage() {
    return driftPercentage;
  }

  /**
   * Percentage of drift from target allocation of assets (Required)
   */
  public void setDriftPercentage(Integer driftPercentage) {
    this.driftPercentage = driftPercentage;
  }

  /**
   * Type of model (Required)
   */
  public Model.ModelType getModelType() {
    return modelType;
  }

  /**
   * Type of model (Required)
   */
  public void setModelType(Model.ModelType modelType) {
    this.modelType = modelType;
  }

  /**
   * Frequency to rebalance model (Required)
   */
  public Model.RebalanceFrequency getRebalanceFrequency() {
    return rebalanceFrequency;
  }

  /**
   * Frequency to rebalance model (Required)
   */
  public void setRebalanceFrequency(Model.RebalanceFrequency rebalanceFrequency) {
    this.rebalanceFrequency = rebalanceFrequency;
  }

  /**
   * Asset Allocations (Required)
   */
  public List<AssetAllocation> getAssetAllocations() {
    return assetAllocations;
  }

  /**
   * Asset Allocations (Required)
   */
  public void setAssetAllocations(List<AssetAllocation> assetAllocations) {
    this.assetAllocations = assetAllocations;
  }

  /**
   * return Id
   *
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * set Id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * return advisorId
   *
   * @return advisorId
   */
  public String getAdvisorId() {
    return advisorId;
  }

  /**
   * set advisorId
   */
  public void setAdvisorId(String advisorId) {
    this.advisorId = advisorId;
  }


  /**
   *
   * return model instance
   *
   * @param id
   * @param advisorId
   * @param name
   * @param description
   * @param cashHoldingPercentage
   * @param driftPercentage
   * @param modelType
   * @param rebalanceFrequency
   * @param assetAllocations
   * @return model
   */
  public static Model of(Long id, String advisorId, String name, String description,
      Integer cashHoldingPercentage, Integer driftPercentage,
      Model.ModelType modelType, Model.RebalanceFrequency rebalanceFrequency,
      List<AssetAllocation> assetAllocations) {

    Model m = new Model();
    m.setId(id);
    m.setAdvisorId(advisorId);
    m.setName(name);
    m.setDescription(description);
    m.setCashHoldingPercentage(cashHoldingPercentage);
    m.setDriftPercentage(driftPercentage);
    m.setModelType(modelType);
    m.setRebalanceFrequency(rebalanceFrequency);
    m.setAssetAllocations(assetAllocations);

    return m;
  }

  /**
   * ModelType Enum
   */
  public enum ModelType {

    QUALIFIED("QUALIFIED"),
    TAXABLE("TAXABLE");
    private final String value;
    private final static Map<String, Model.ModelType> CONSTANTS = new HashMap<String, Model.ModelType>();

    static {
      for (Model.ModelType c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private ModelType(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    public String value() {
      return this.value;
    }

    public static Model.ModelType fromValue(String value) {
      Model.ModelType constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }

  }

  /**
   * RebalanceFrequency Enum
   */
  public enum RebalanceFrequency {

    MONTHLY("MONTHLY"),
    QUARTERLY("QUARTERLY"),
    SEMI_ANNUAL("SEMI_ANNUAL"),
    ANNUAL("ANNUAL");
    private final String value;
    private final static Map<String, Model.RebalanceFrequency> CONSTANTS = new HashMap<String, Model.RebalanceFrequency>();

    static {
      for (Model.RebalanceFrequency c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private RebalanceFrequency(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    public String value() {
      return this.value;
    }

    public static Model.RebalanceFrequency fromValue(String value) {
      Model.RebalanceFrequency constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }

  }

}
