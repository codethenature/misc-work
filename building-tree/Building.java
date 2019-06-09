package assignment3;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;

	public Building(OneBuilding data) {
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}

	public String toString() {
		String result = this.data.toString() + "\n";
		if (this.older != null) {
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null) {
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null) {
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}

	public Building addBuilding(OneBuilding b) {
		if (b == null)
			return this;
		// Doing the same thing in each condition
		// Just because of can't add helper functions
		if (this.data.yearOfConstruction < b.yearOfConstruction) {
			if (this.younger != null) {
				// To avoid go back trace to find parent
				if (this.younger.data.yearOfConstruction == b.yearOfConstruction
						&& this.younger.data.height < b.height) {
					// This case means we have to rearrange the tree
					// Because of adding the node in between of nodes
					Building temp = this.younger;
					this.younger = new Building(b);
					this.younger.older = temp.older;
					this.younger.younger = temp.younger;
					this.younger.same = temp;
					temp.older = null;
					temp.younger = null;
				} else {
					this.younger.addBuilding(b);
				}

			} else {
				this.younger = new Building(b);
			}
		} else if (this.data.yearOfConstruction > b.yearOfConstruction) {
			if (this.older != null) {
				// To avoid go back trace to find parent
				if (this.older.data.yearOfConstruction == b.yearOfConstruction && this.older.data.height < b.height) {

					Building temp = this.older;
					this.older = new Building(b);
					this.older.older = temp.older;
					this.older.younger = temp.younger;
					this.older.same = temp;
					temp.older = null;
					temp.younger = null;
				} else {
					this.older.addBuilding(b);
				}
			} else {
				this.older = new Building(b);
			}
		} else {
			// Check Height
			if (this.data.height < b.height) {
				// only possible in topmost root case
				Building root = new Building(b);
				root.older = this.older;
				root.younger = this.younger;
				this.older = null;
				this.younger = null;
				root.same = this;
				return root;
			} else if (this.data.height > b.height) {
				// go down
				if (this.same != null) {
					if (this.same.data.height < b.height) {
						Building temp = this.same;
						this.same = new Building(b);
						this.same.older = temp.older;
						this.same.younger = temp.younger;
						this.same.same = temp;
						temp.older = null;
						temp.younger = null;
					} else {
						this.same.addBuilding(b);
					}
				} else {
					this.same = new Building(b);
				}

			}
		}
		return this;
	}

	public Building addBuildings(Building b) {
		if (b == null)
			return this;
		this.addBuilding(b.data);

		if (b.older != null) {
			this.addBuildings(b.older);
		}
		if (b.same != null) {
			this.addBuildings(b.same);
		}
		if (b.younger != null) {
			this.addBuildings(b.younger);
		}
		return this;
	}

	public Building removeBuilding(OneBuilding b) {
		if (b == null)
			return this;
		// Found
		if (this.data.equals(b)) {
			if (this.same != null) {
				return this.same.addBuildings(this.older).addBuildings(this.younger);
			} else if (this.older != null) {
				return this.older.addBuildings(this.younger);
			} else {
				return this.younger;
			}
		}

		// Not Found, Try to find in children
		if (this.older != null) {
			if (this.older.data.equals(b)) {
				// Same like add method, rearrange the tree in each condition
				Building temp;
				if (this.older.same != null) {
					temp = this.older.same.addBuildings(this.older.older).addBuildings(this.older.younger);
				} else if (this.older.older != null) {
					temp = this.older.older.addBuildings(this.older.younger);
				} else {
					temp = this.older.younger;
				}
				this.older = temp;
			} else {
				this.older.removeBuilding(b);
			}
		}

		if (this.same != null) {
			if (this.same.data.equals(b)) {
				Building temp;
				if (this.same.same != null) {
					temp = this.same.same.addBuildings(this.same.older).addBuildings(this.same.younger);
				} else if (this.same.older != null) {
					temp = this.same.older.addBuildings(this.same.younger);
				} else {
					temp = this.same.younger;
				}
				this.same = temp;
			} else {
				this.same.removeBuilding(b);
			}
		}
		if (this.younger != null) {
			if (this.younger.data.equals(b)) {
				Building temp;
				if (this.younger.same != null) {
					temp = this.younger.same.addBuildings(this.younger.older).addBuildings(this.younger.younger);
				} else if (this.younger.older != null) {
					temp = this.younger.older.addBuildings(this.younger.younger);
				} else {
					temp = this.younger.younger;
				}
				this.younger = temp;
			} else {
				this.younger.removeBuilding(b);
			}
		}
		return this;
	}

	public int oldest() {
		// Find leftmost older children.
		if (this.older != null) {
			return this.older.oldest();
		} else {
			return this.data.yearOfConstruction;
		}
	}

	public int highest() {
		// Finding max height between current and recursively [older, same and younger
		// buildings].
		return Math.max(this.older != null ? this.older.highest() : 0,
				Math.max(this.younger != null ? this.younger.highest() : 0,
						Math.max(this.same != null ? this.same.highest() : 0, this.data.height)));

	}

	public OneBuilding highestFromYear(int year) {
		// First go the respective year ad return the height (Hint - BST).
		if (year < this.data.yearOfConstruction) {
			return this.older != null ? this.older.highestFromYear(year) : null;
		} else if (year > this.data.yearOfConstruction) {
			return this.younger != null ? this.younger.highestFromYear(year) : null;
		} else {
			return this.data;
		}
	}

	public int numberFromYears(int yearMin, int yearMax) {
		// Check the current year of construction if lies in the range then add it and
		// recurse other children.
		// If not present in the range the go either left or right.
		if (yearMax < yearMin)
			return 0;
		if (this.data.yearOfConstruction < yearMin) {
			return this.younger != null ? this.younger.numberFromYears(yearMin, yearMax) : 0;
		} else if (this.data.yearOfConstruction > yearMax) {
			return this.older != null ? this.older.numberFromYears(yearMin, yearMax) : 0;
		} else {
			return (int) (this.same != null ? this.same.numberFromYears(yearMin, yearMax) : 0)
					+ (int) (this.older != null ? this.older.numberFromYears(yearMin, yearMax) : 0)
					+ (int) (this.younger != null ? this.younger.numberFromYears(yearMin, yearMax) : 0) + 1;
		}
	}

	public int[] costPlanning(int nbYears) {
		// Good one. Can't modify the structure of function
		// so have to go iteratively. Used stack means DFS
		// Same concept applies here too like the above function.
		// If lies in the range then add the cost otherwise go either left or right.
		Building root = this;
		int[] ans = new int[nbYears];

		java.util.Stack<Building> st = new java.util.Stack<>();
		st.push(root);

		while (!st.isEmpty()) {
			Building current = st.pop();

			if (current.data.yearForRepair - 2018 >= 0 && current.data.yearForRepair - 2018 < nbYears) {
				// means this building in our range. Add it.
				ans[current.data.yearForRepair - 2018] += current.data.costForRepair;

				if (current.same != null)
					st.push(current.same);
				if (current.older != null)
					st.push(current.older);
				if (current.younger != null)
					st.push(current.younger);
			}

			if (current.data.yearForRepair - 2018 < 0 && current.younger != null) {
				// means its older than 2018
				st.push(current.younger);
			}
			if (current.data.yearForRepair - 2018 >= nbYears && current.older != null) {
				// means its more younger than 2018+nbyears
				st.push(current.older);
			}

		}
		return ans;
	}

}
