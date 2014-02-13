package thejavahub;


	public enum material {
		WOOD (1, 1, 1),
		STONE (2, 1, 1), 
		STEEL (3, 1, 1),
		MITHRIL(4, 1, 1);
		
		private int damage;
		private int durability;
		private int resistance;
		
		
		material(int damage, int durability, int resistance){
			this.setDamage(damage);
			this.setDurability(durability);
			this.setResistance(resistance);
		}

		public int getDamage() {
			return damage;
		}

		public void setDamage(int damage) {
			this.damage = damage;
		}

		public int getDurability() {
			return durability;
		}

		public void setDurability(int durability) {
			this.durability = durability;
		}

		public int getResistance() {
			return resistance;
		}

		public void setResistance(int resistance) {
			this.resistance = resistance;
		}
		
	}



