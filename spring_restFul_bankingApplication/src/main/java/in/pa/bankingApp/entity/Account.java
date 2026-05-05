package in.pa.bankingApp.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accNum")
	@SequenceGenerator(name = "accNum", sequenceName = "accNumber", initialValue = 50501, allocationSize = 1)
	@Column
	private long accNum;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private long mobileNo;
	
	@Column(nullable = false)
	private String email;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	@Column(nullable = false)
	private LocalDate dob;
	
	@Column(unique = true, nullable = false)
	private String aadharNum;
	
	@Column(unique = true, nullable = false)
	private String panNum;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	@Column(nullable = false)
	private LocalDate accOpeningDate;
	
	@Column
	private double balance;

}
