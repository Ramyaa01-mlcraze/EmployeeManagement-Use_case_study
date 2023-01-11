import { Employee } from './employee';

describe('Employee', () => {

  /* Should create an Employee instance */
  it('should create an instance', () => {
    expect(new Employee()).toBeTruthy();
  });
});
