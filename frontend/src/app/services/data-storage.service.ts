import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {
  private storageKey = 'authToken';

  saveData(data: any): void {
    localStorage.setItem(this.storageKey, JSON.stringify(data));
  }

  getData(): any {
    const storedData = localStorage.getItem(this.storageKey);
    return storedData ? JSON.parse(storedData) : null;
  }
}
