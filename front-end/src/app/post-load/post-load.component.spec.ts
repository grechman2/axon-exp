import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostLoadComponent } from './post-load.component';

describe('PostLoadComponent', () => {
  let component: PostLoadComponent;
  let fixture: ComponentFixture<PostLoadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostLoadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostLoadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
