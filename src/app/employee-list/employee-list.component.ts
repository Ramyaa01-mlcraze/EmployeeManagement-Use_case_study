import { Component, OnInit } from '@angular/core';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { Observable } from 'rxjs';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {saveAs} from 'file-saver';
import { TokenStorageService } from '../services/token-storage.service';
import { ViewChild } from '@angular/core';



@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  @ViewChild("insideElement") insideElement : any;
  
  employees!: Observable<Employee[]>;
  searchText:any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 4;
  tableSizes: any = [3, 6, 9, 12];
  dataRefresher:any;
  showDownload=false;
  public roles: string[] = [];
  isLoggedIn = false;
  showActions=false;
  showCheck=false;
  Employee : Employee[]=[];
  checked!: boolean;
  id!:number;
  p:any;
  temp: Employee = new Employee();
  isEdit!: false;

  /* Should call inline update */
  inLineUpdate(inLineId: number) {    

    this.employeeService.updateEmployee(this.id, this.temp).subscribe( data =>{
      this.ngOnInit();  
      alert("Employee is successfully updated");
    }
    , error => console.log(error));
  }

   /* Should call change value method */
  changeValue(value : any) {
    this.checked = !value;
  }
  SelectedIDs: number[] = [];
  items = [];

  constructor(private employeeService: EmployeeService,
    private router: Router,
    private http: HttpClient, public tokenStorageService: TokenStorageService) {
      
    }
    selectedEmployee! : Employee[];

  ngOnInit() : void{
    this.reloadData();
    this.isLoggedIn=!!this.tokenStorageService.getToken();
    
    if(this.isLoggedIn){
      const user=this.tokenStorageService.getUser();
      this.roles=user.roles;
      this.showDownload=this.roles?.includes('ROLE_ADMIN');
      this.showActions=this.roles?.includes('ROLE_ADMIN');
      this.showCheck=this.roles?.includes('ROLE_ADMIN');
    }
    
  }

  /* Should call field method */
  fieldsChange(values:any):void {
    console.log(values.currentTarget?.checked);
  }

   /* Should call change page method */
  pageChange($event : any) {
    this.p = $event;
  }  
  
  /* Should call selectID method */
  selectID(id:number, event:any){
    this.changeValue(this.checked)
    this.SelectedIDs.push(id);
  }

  /* Should delete the selected */
  deleteSelected(){
    if(confirm('Are you sure you want to delete the selected ?')) {
      var unique = this.SelectedIDs.filter(function(elem, index, self) {
        return index === self.indexOf(elem);
    })
      for(var index in unique) {   
        this.employeeService.deleteEmployee(this.SelectedIDs[index]).subscribe( data => {
          this.reloadData();
        })
      }
      this.SelectedIDs.splice(0);
    }
  }

  /* Should reload the data */
  reloadData() : void{
    this.employees = this.employeeService.getEmployeesList();
  }

  /* Should delete the employee by id */
  deleteEmployee(id:number){
    if(confirm("Are you sure to delete the record?"))
        this.employeeService.deleteEmployee(id)
          .subscribe(
            data => {
              alert("Record deleted successfully")
              this.reloadData();
            },
            error => console.log(error)
          );
  }

   /* Should call employeeDetails method */
  employeeDetails(id: number) {
    const url = '/details/' + id;
    this.router.navigateByUrl(url);
} 

  /* Should call TableDataChange method */
  onTableDataChange(event: any) {
    this.page = event;
    this.reloadData();
  }

  /* Should call TableSizeChange method */
  onTableSizeChange(event: any): void {
    this.tableSize = event.target?.value;
    this.page = 1;
    this.reloadData();
  }

  /* Should download the excel */
  downloadExcel(){
    this.employeeService.exportExcel().subscribe(xls => {
      const blob = new Blob([xls]);
      const fileName = `Employees-list_${(new Date().toJSON().slice(0,10))}.xlsx`
      saveAs(blob, fileName)
    },
    error => {
      console.log('error');
    });
  }

  /* Should call download PDF method */
  downloadPdf(){
    this.employeeService.exportPdf().subscribe(pdf => {
      const blob = new Blob([pdf]);
      const fileName = `Employee-list_${(new Date().toJSON().slice(0,10))}.pdf`
      saveAs(blob, fileName)
    },
    error => {
      console.log('error');
    });
  }

  /* Should call download CSV method */
  downloadCsv(){
    this.employeeService.exportCsv().subscribe(csv => {
      const blob = new Blob([csv], {type:'text/csv'});
      const fileName = `Employee-list_${(new Date().toJSON().slice(0,10))}.csv` 
      saveAs(blob, fileName)
    },
    error => {
      console.log('error');
    });
  }

  /* Should do group delete action */
  onClickGroupDelete(): boolean {
    return false;
  }
  
   /* Should call onClick method */
  public onClick(targetElement : any) {
    const clickedInside = this.insideElement?.nativeElement.contains(targetElement);
    if (!clickedInside) {
      console.log('outside clicked');
    }
  }

   /* Should call onEdit method */
  onEdit(item: any) {
    this.id = item.id;    
    this.employeeService.getEmployee(this.id).subscribe(data => {
      this.temp = data;
    });
    this.Employee.forEach(element => {
      element.isEdit = false;
    })
    item.isEdit = true;
  }

/* Should refresh data */
  refreshData(){
    this.dataRefresher = 
      setInterval(() => {
        this.reloadData();
      },10000);
  }
  
}
