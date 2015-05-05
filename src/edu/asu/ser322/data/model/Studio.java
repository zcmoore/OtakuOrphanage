package edu.asu.ser322.data.model;

import java.util.Date;
import java.util.List;

/**
 * Model of an animation studio, which owns franchises and employs general staff.
 * <p>
 * The list of franchises returned by {@link #getFranchises()} will include all franchises
 * which this studio is involved in, in whole or part (e.g. collaborations with other
 * studios).
 * <p>
 * Note that {@link #generalStaff} typically only includes individuals who are involved
 * with the studio at a high level, and does not typically include all staff, especially
 * those who work only on a small number of the studios projects. That is, a studio may
 * have "indirect" employees from the episodes they own.
 * 
 * @author Moore, Zachary
 *
 */
public class Studio
{
	public static final Studio NULL_STUDIO = new Studio();
	private String name;
	
	/** If {@link #name} is changed, the original name will be stored here. */
	private String previousName;
	private Date startDate;
	private Date closeDate;
	
	private List<Person> generalStaff;
	private List<Franchise> franchises;
	
	public Studio()
	{
		super();
	}
	
	public Studio(String name, Date startDate)
	{
		super();
		this.name = name;
		this.startDate = startDate;
	}
	
	public Studio(String name, Date startDate, Date closeDate)
	{
		super();
		this.name = name;
		this.startDate = startDate;
		this.closeDate = closeDate;
	}
	
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets this studio's {@link #name}, as returned by {@link #getName()}, and updates
	 * the {@link #previousName} if applicable.
	 * <p>
	 * Note that {@link #previousName} is intended for use by a persistent store, which
	 * may need this information to find and update the correct object.
	 * 
	 * @param name
	 *            New name of this studio
	 */
	public void setName(String name)
	{
		if (this.previousName == null)
		{
			this.previousName = this.name;
		}
		
		this.name = name;
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	
	public Date getCloseDate()
	{
		return closeDate;
	}
	
	public void setCloseDate(Date closeDate)
	{
		this.closeDate = closeDate;
	}
	
	public String getPreviousName()
	{
		return previousName;
	}
	
	public void setPreviousName(String previousName)
	{
		this.previousName = previousName;
	}
	
	public List<Person> getGeneralStaff()
	{
		return generalStaff;
	}
	
	public void setGeneralStaff(List<Person> generalStaff)
	{
		this.generalStaff = generalStaff;
	}
	
	public List<Franchise> getFranchises()
	{
		return franchises;
	}
	
	public void setFranchises(List<Franchise> franchises)
	{
		this.franchises = franchises;
	}
}
