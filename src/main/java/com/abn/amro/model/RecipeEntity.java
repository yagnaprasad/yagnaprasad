package com.abn.amro.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/* RECIPE Entity class with one to many mapping */

@Entity
@Table(name="RECIPE")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recipeId")
    private Long id;
    
    @Column(name="name")
    private String name;


    @Column(name="price")
    private Float price;

	public RecipeEntity() {
	}

	public RecipeEntity(String name, Float price,String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}

	@OneToMany(mappedBy = "recipe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<IngredientsEntity> ingridientEntities;


	public Set<IngredientsEntity> getIngridientEntities() {
		return ingridientEntities;
	}

	public void setIngridientEntities(Set<IngredientsEntity> ingridientEntities) {
		this.ingridientEntities = ingridientEntities;
	}

	/**
	 * Returns a hash code value for the object. This method is
	 * supported for the benefit of hash tables such as those provided by
	 * {@link HashMap}.
	 * <p>
	 * The general contract of {@code hashCode} is:
	 * <ul>
	 * <li>Whenever it is invoked on the same object more than once during
	 *     an execution of a Java application, the {@code hashCode} method
	 *     must consistently return the same integer, provided no information
	 *     used in {@code equals} comparisons on the object is modified.
	 *     This integer need not remain consistent from one execution of an
	 *     application to another execution of the same application.
	 * <li>If two objects are equal according to the {@code equals(Object)}
	 *     method, then calling the {@code hashCode} method on each of
	 *     the two objects must produce the same integer result.
	 * <li>It is <em>not</em> required that if two objects are unequal
	 *     according to the {@link Object#equals(Object)}
	 *     method, then calling the {@code hashCode} method on each of the
	 *     two objects must produce distinct integer results.  However, the
	 *     programmer should be aware that producing distinct integer results
	 *     for unequal objects may improve the performance of hash tables.
	 * </ul>
	 * <p>
	 * As much as is reasonably practical, the hashCode method defined
	 * by class {@code Object} does return distinct integers for
	 * distinct objects. (The hashCode may or may not be implemented
	 * as some function of an object's memory address at some point
	 * in time.)
	 *
	 * @return a hash code value for this object.
	 * @see Object#equals(Object)
	 * @see System#identityHashCode
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}



	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "created_at")
	private String createdAt;

	@Column(name="type")
    private String type;

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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}