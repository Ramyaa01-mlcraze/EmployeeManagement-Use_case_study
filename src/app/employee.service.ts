import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {


  constructor(private http: HttpClient) { }

  /* Should retrieve an Employee with ID on Service method */
  getEmployee(id: number): Observable<any>{
    let baseUrl = environment.baseUrl;
    return this.http.get(`${baseUrl}/${id}`);
  }

  /* Should create an Employee on Service method */
  createEmployee(employee: Object): Observable<Object>{
    let baseUrl = environment.baseUrl;
    return this.http.post(`${baseUrl}`,employee);
  }

  /* Should update an Employee on Service method */
  updateEmployee(id: number, value:any): Observable<Object>{
    let baseUrl = environment.baseUrl;
    return this.http.put(`${baseUrl}/${id}`,value);
  }

  /* Should delete an Employee on Service method */
  deleteEmployee(id: number): Observable<any>{
    let baseUrl = environment.baseUrl;
    return this.http.delete(`${baseUrl}/${id}`,{responseType:'text'});
  }

  /* Should retrieve employees on Service method */
  getEmployeesList(): Observable<any>{
    let baseUrl = environment.baseUrl;
    return this.http.get(`${baseUrl}`);
  }

  /* Should export as Excel file */
  exportExcel(): Observable<Blob>{
    let exportUrl = environment.exportUrl;
    let paramsXlsx = new HttpParams().set("type","xlsx");
    return this.http.get(`${exportUrl}`,{params:paramsXlsx, responseType:'blob'});
  }

  /* Should export as PDF file */
  exportPdf(): Observable<Blob>{
    let exportUrl = environment.exportUrl;
    let paramsPdf = new HttpParams().set("type","pdf");
    return this.http.get(`${exportUrl}`,{params:paramsPdf, responseType:'blob'});
  }

  /* Should export as CSV file */
  exportCsv(): Observable<Blob>{
    let exportUrl = environment.exportUrl;
    let paramsCsv = new HttpParams().set("type","csv");
    return this.http.get(`${exportUrl}`,{params:paramsCsv, responseType:'blob'});
  }



}
