package services;

import java.util.List;

import dto.DepartDTO;
import persistences.DepartDAO;

public class Depart {

	public List<DepartDTO> getAllDeparts() {
		List<DepartDTO> allDeparts = null;

		allDeparts = new DepartDAO().getAllDeparts();

		return allDeparts;
	}

	public List<DepartDTO> getSomeDeparts(String unitid, String unitname) {
		List<DepartDTO> allDeparts = null;

		allDeparts = new DepartDAO().getSomeDeparts(unitid, unitname);

		return allDeparts;
	}

	public DepartDTO getDepartById(String id) {
		DepartDTO depart = null;

		depart = new DepartDAO().getDepartById(id);
		return depart;
	}

	public DepartDTO getDepartByName(String name) {
		DepartDTO depart = null;

		depart = new DepartDAO().getDepartByName(name);
		return depart;
	}

	public int getDepartcount() {
		return new DepartDAO().getDepartCount();
	}

	public boolean delUnit(String ID) {

		boolean saveSuccessful = false;
		saveSuccessful = new DepartDAO().delUnit(ID);
		return saveSuccessful;
	}

	public boolean addUnit(DepartDTO departDTO) {

		boolean saveSuccessful = false;
		saveSuccessful = new DepartDAO().addUnit(departDTO);
		return saveSuccessful;
	}

	public boolean updateDepart(DepartDTO departDTO) {

		boolean saveSuccessful = false;
		saveSuccessful = new DepartDAO().updateDepart(departDTO);
		return saveSuccessful;
	}

}
