var person  = {};

Object.defineProperty(person,"name",{
    configurable:false,
    value:"hello"
});
console.log(person.name);
delete(person.name);
console.log(person.name);