
public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	private boolean [] deleted;
	public int size;
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.deleted = new boolean[m];
		for(int i = 0; i < m; i ++){
			this.deleted[i] = false;
		}
		this.size = m;
	}
	
	
	@Override
	public HashTableElement Find(long key) {
		for(int i = 0; i < this.size; i++){
			int index = Hash(key , i);
			if (this.table[index] != null){
				if (this.table[index].GetKey() == key){
					return this.table[index];
				}
			}
			else if(this.deleted[index] == false){
				return null;
			}
		}
		return null;
	}
	
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		if( Find(hte.GetKey()) != null) { throw new KeyAlreadyExistsException(hte); }
		for (int i = 0; i < this.size; i++){
			int index = Hash(hte.GetKey(), i);
			if (this.table[index] == null){
				this.table[index] = hte;
				this.deleted[index] = false;
				return;
			}
		}
		throw new TableIsFullException(hte);
	}
	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		if (Find(key) == null){ throw new KeyDoesntExistException(key);}
		for (int i = 0; i < this.size; i ++){
			int index = Hash(key , i);
			if (this.table[index] != null && this.table[index].GetKey() == key){
				this.table[index] = null;
				this.deleted[index] = true;
				return;
			}

		}
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
