import OurTable from "main/components/OurTable";

export default function StudentsTable({ students }) {
    const columns = [
        {
            Header: 'id',
            accessor: 'id', // accessor is the "key" in the data
        },
        {
            Header: 'First Name',
            accessor: 'firstName',
        },
        {
            Header: 'Last Name',
            accessor: 'lastName',
        },
        {
            Header: 'Perm',
            accessor: 'perm',
        }
    ];

    return <OurTable
        data={students}
        columns={columns}
        testid={"StudentsTable"}
    />;
};